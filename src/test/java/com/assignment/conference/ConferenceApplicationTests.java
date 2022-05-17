package com.assignment.conference;

import com.assignment.conference.management.dto.*;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConferenceApplicationTests {

	@LocalServerPort
	int port;

	@BeforeEach
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	void should_get_conference_info() {
		ConferenceDetailsResponseDto responseDto = authenticateAsUser().when()
				.get("/api/conference/1")
				.then().statusCode(200).extract()
				.as(ConferenceDetailsResponseDto.class);
		assertEquals("Conference_1", responseDto.getName());
		assertEquals("Conference with max entries", responseDto.getDescription());
		assertEquals(2, responseDto.getMaxEntries());
		assertEquals(2, responseDto.getEntries());
	}

	@Test
	void should_return_404_when_conference_not_found() {
		authenticateAsUser().when()
				.get("/api/conference/100")
				.then().statusCode(404);
	}

	@Test
	void should_add_new_conference() {
		 authenticateAsAdmin().when().body(createConferenceAddRequestDto()).contentType(ContentType.JSON)
				.request("POST", "/api/conference/new")
				.then().statusCode(201);
	}

	@Test
	void should_not_allow_adding_conference_for_without_admin_role() {
		authenticateAsUser().when().body(createConferenceAddRequestDto()).contentType(ContentType.JSON)
				.request("POST", "/api/conference/new")
				.then().statusCode(403);
	}

	@Test
	void should_validate_conference_input() {
		RestErrorDto restErrorDto = authenticateAsAdmin()
				.body(new ConferenceAddRequestDto()).contentType(ContentType.JSON)
				.request("POST", "/api/conference/new")
				.then().statusCode(400)
				.extract().as(RestErrorDto.class);
		assertEquals("Request is not valid", restErrorDto.getError());
		assertEquals(4, restErrorDto.getFieldErrorList().size());
	}

	@Test
	void should_add_new_participant() {
		authenticateAsUser()
				.body(createParticipantRegisterRequestDto()).contentType(ContentType.JSON)
				.request("POST", "/api/conference/3/register")
				.then().statusCode(200);
	}

	@Test
	void should_cancel_conference() {
		authenticateAsAdmin()
				.body(createCancelConferenceRequestDto(4L)).contentType(ContentType.JSON)
				.request("PUT", "/api/conference/cancel")
				.then().statusCode(200);
	}

	@Test
	void should_not_allow_cancelling_conference_without_admin_role() {
		authenticateAsUser()
				.body(createCancelConferenceRequestDto(4L)).contentType(ContentType.JSON)
				.request("PUT", "/api/conference/cancel")
				.then().statusCode(403);
	}

	@Test
	void should_validate_participant_input() {
		RestErrorDto restErrorDto = authenticateAsUser()
				.body(new ParticipantRegisterRequestDto()).contentType(ContentType.JSON)
				.request("POST", "/api/conference/3/register")
				.then().statusCode(400)
				.extract().as(RestErrorDto.class);
		assertEquals("Request is not valid", restErrorDto.getError());
		assertEquals(2, restErrorDto.getFieldErrorList().size());
	}

	@Test
	void should_remove_participant() {
		authenticateAsAdmin().body(createParticipantUnregisterRequestDto(3L)).contentType(ContentType.JSON)
				.request("DELETE", "/api/conference/unregister")
				.then().statusCode(200);
	}

	@Test
	void should_not_allow_removing_participant_without_admin_role() {
		authenticateAsUser().body(createParticipantUnregisterRequestDto(3L)).contentType(ContentType.JSON)
				.request("DELETE", "/api/conference/unregister")
				.then().statusCode(403);
	}

	@Test
	void should_not_allow_registering_to_conference_when_max_entries_reached() {
		RestErrorDto restErrorDto = authenticateAsUser()
				.body(createParticipantRegisterRequestDto()).contentType(ContentType.JSON)
				.request("POST", "/api/conference/1/register")
				.then().statusCode(400)
				.extract().as(RestErrorDto.class);
		assertEquals("Conference is full, cannot register.", restErrorDto.getError());
	}

	@Test
	void should_not_allow_registering_to_cancelled_conference() {
		RestErrorDto restErrorDto = authenticateAsUser()
				.body(createParticipantRegisterRequestDto()).contentType(ContentType.JSON)
				.request("POST", "/api/conference/2/register")
				.then().statusCode(400)
				.extract().as(RestErrorDto.class);
		assertEquals("Conference is canceled, cannot register.", restErrorDto.getError());
	}

	private ConferenceCancelRequestDto createCancelConferenceRequestDto(Long conferenceId) {
		ConferenceCancelRequestDto requestDto = new ConferenceCancelRequestDto();
		requestDto.setConferenceId(conferenceId);
		return requestDto;
	}

	private ParticipantUnregisterRequestDto createParticipantUnregisterRequestDto(Long participantId) {
		ParticipantUnregisterRequestDto requestDto = new ParticipantUnregisterRequestDto();
		requestDto.setParticipantId(participantId);
		return requestDto;
	}

	private ConferenceAddRequestDto createConferenceAddRequestDto() {
		ConferenceAddRequestDto requestDto = new ConferenceAddRequestDto();
		requestDto.setName("Test conference name");
		requestDto.setDescription("Test conference description");
		requestDto.setStartTime(LocalDate.now().plusDays(10).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		requestDto.setMaxEntries(10L);
		return requestDto;
	}

	private ParticipantRegisterRequestDto createParticipantRegisterRequestDto() {
		ParticipantRegisterRequestDto requestDto = new ParticipantRegisterRequestDto();
		requestDto.setName("Test Name");
		requestDto.setEmail("test@email.com");
		return requestDto;
	}

	private static RequestSpecification authenticateAsAdmin() {
		return given().auth()
				.basic("test_admin", "admin");
	}

	private static RequestSpecification authenticateAsUser() {
		return given().auth()
				.basic("test_user", "user");
	}

}
