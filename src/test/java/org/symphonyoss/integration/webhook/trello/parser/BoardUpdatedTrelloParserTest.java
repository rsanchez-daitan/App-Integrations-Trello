/**
 * Copyright 2016-2017 Symphony Integrations - Symphony LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.symphonyoss.integration.webhook.trello.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.symphony.api.pod.client.ApiException;
import org.symphonyoss.integration.service.model.ConfigurationInstance;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.symphonyoss.integration.entity.model.User;
import org.symphonyoss.integration.webhook.exception.WebHookParseException;

import java.io.IOException;

/**
 * Test class to validate {@link BoardUpdatedTrelloParser}
 * Created by ecarenho on 09/09/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class BoardUpdatedTrelloParserTest extends CommonTrelloTest {

  @InjectMocks
  private BoardUpdatedTrelloParser parser = new BoardUpdatedTrelloParser();

  private ConfigurationInstance instance = new ConfigurationInstance();

  @Before
  public void setup() {
    String optionalProperties = "{ \"notifications\": [\"boardRenamed\"] }";
    instance.setOptionalProperties(optionalProperties);
  }

  @Test
  public void testBoardRenamedWithoutEmail()
      throws IOException, WebHookParseException, ApiException {
    JsonNode rootNode = getJsonFile("payload_trello_board_updated.json");
    assertTrue(parser.filterNotifications(instance, rootNode));

    String result = parser.parse(instance, rootNode);

    assertNotNull(result);

    String expected = readFile("payload_trello_board_updated_without_user_expected_message.xml");
    assertEquals(expected, result);
  }

  @Test
  public void testBoardRenamedWithEmail() throws IOException, WebHookParseException, ApiException {
    JsonNode rootNode = getJsonFile("payload_trello_board_updated.json");

    User user = new User();
    user.setId(699722783L);
    user.setUserName("ecarrenhosymphonytest");
    user.setEmailAddress("ecarrenhosymphonytest@symphony.com");
    user.setDisplayName("Evandro Carrenho @symphony");
    when(userService.getUserByUserName(anyString(), anyString())).thenReturn(user);

    String result = parser.parse(instance, rootNode);
    assertNotNull(result);

    String expected = readFile("payload_trello_board_updated_expected_message.xml");
    assertEquals(expected, result);
  }

  @Test
  public void testIgnoreNotification() throws IOException {
    String optionalProperties = "{ \"notifications\": [\"listCreated\"] }";
    instance.setOptionalProperties(optionalProperties);

    JsonNode rootNode = getJsonFile("payload_trello_board_updated.json");
    assertFalse(parser.filterNotifications(instance, rootNode));
  }
}
