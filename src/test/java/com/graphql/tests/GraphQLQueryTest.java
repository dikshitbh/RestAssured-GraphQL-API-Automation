package com.graphql.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import  static org.hamcrest.Matchers.equalTo;

public class GraphQLQueryTest {

    @Test
    public void getAllFilms(){

        String query = "{\"query\":\"{\\n  allFilms{\\n    films{\\n      title\\n    }\\n  }\\n}\"}";

        RestAssured.baseURI = "https://swapi-graphql.netlify.app/";
                given().log().all()
                .contentType("application/json")
                .body(query)
                        .post(".netlify/functions/index")
                            .then()
                                .log().all()
                                    .assertThat()
                                        .statusCode(200)
                                            .and()
                                                .body("data.allFilms.films[0].title",equalTo("A New Hope"));
    }


    @Test
    public void getAllUsers(){

        RestAssured.baseURI  = "https://hasura.io";

        String query = "{\"query\":\"{\\n  users(limit: 10) {\\n    id\\n    name\\n  }\\n}\\n\",\"variables\":null}";

        given()
                .contentType("application/json")
                .header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDY2NDIzZmI2Mzk5NjQ5M2MyNjY2ZTIwZCJ9LCJuaWNrbmFtZSI6ImRpa3NoaXRiaGFyZHdhaiIsIm5hbWUiOiJkaWtzaGl0YmhhcmR3YWpAeWFob28uY29tIiwicGljdHVyZSI6Imh0dHBzOi8vcy5ncmF2YXRhci5jb20vYXZhdGFyL2RmNzUwN2E2Nzk3MmFmN2ZkZmVlMjU2OThiN2UxNmZmP3M9NDgwJnI9cGcmZD1odHRwcyUzQSUyRiUyRmNkbi5hdXRoMC5jb20lMkZhdmF0YXJzJTJGZGkucG5nIiwidXBkYXRlZF9hdCI6IjIwMjQtMDUtMTNUMTY6Mjg6MzkuMzM1WiIsImlzcyI6Imh0dHBzOi8vZ3JhcGhxbC10dXRvcmlhbHMuYXV0aDAuY29tLyIsImF1ZCI6IlAzOHFuRm8xbEZBUUpyemt1bi0td0V6cWxqVk5HY1dXIiwiaWF0IjoxNzE1NjU0MDkxLCJleHAiOjE3MTU2OTAwOTEsInN1YiI6ImF1dGgwfDY2NDIzZmI2Mzk5NjQ5M2MyNjY2ZTIwZCIsImF0X2hhc2giOiJqdjRLU3JWZjRPejV2ZUNDYTBST3p3Iiwic2lkIjoiRWhhbFdDX3RiMEkzX25jQTY2VU4wazlMeWxSd1lqYUgiLCJub25jZSI6Iml1Wk5Ec3RBYVEtUVVlZDFBVWhaMVVfTUFQfmY0Y3pUIn0.Fcq7PKZoHTCBZ6l1Bx_-mxDobvEcA8pduh1ryAfBJKCGN9KYyL3pAjA2Iz_9P2bq1nYL88nU-iaUZpXWuKXSgdsH5j_yfZ5gzAvskD5PQUssUVdptdS0Kev1wbbQVs0rn7ozNSGV6JKuOI-nqvzNHGVmKPJl1c1zMErih68s4v0NPCXwCrs8eSfNcFyTzqQrRAavKIiFOVWDNK8x94WwK6qPhasub0UwQ_Z7Mzo3KpiiDq6nHKFx_KPpt-TgpENr1UGEErMibni2MTeKNBgWM5SzgvwshWGqOpSMpnwNsCLT-2x6b5suKKXZUewQnUMRmYw0fuJdXQ-YfL5cMy7cUg")
                    .log().all()
                        .when()
                            .body(query)
                                .post("/learn/graphql")
                                    .then().log().all()
                                            .assertThat().statusCode(200);

    }

}
