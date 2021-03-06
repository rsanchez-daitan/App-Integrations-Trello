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

package org.symphonyoss.integration.webhook.trello.model;

import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.CHECKLIST;
import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.ID;
import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.INTEGRATION_NAME;
import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.NAME;

import com.fasterxml.jackson.databind.JsonNode;
import org.symphonyoss.integration.entity.Entity;
import org.symphonyoss.integration.entity.EntityBuilder;

/**
 * A class to build an entity from a Checklist JSON node contained on Trello payloads.
 * Created by ecarrenho on 09/13/16.
 */
public class TrelloChecklist {

  private JsonNode rootNode;

  /**
   * Constructs a Checklist entity from a Checklist JSON node contained on Trello payloads.
   * @param rootNode Checklist root node from Trello
   */
  public TrelloChecklist(JsonNode rootNode) {
    this.rootNode = rootNode;
  }

  /**
   * Getter for Checklist id.
   * @return Checklist id.
   */
  public String getId() {
    return rootNode.path(ID).asText();
  }

  /**
   * Getter for Checklist name.
   * @return Checklist name.
   */
  public String getName() {
    return rootNode.path(NAME).asText();
  }

  /**
   * Builds the Checklist entity from Trello payload data.
   * @return Checklist entity with id and name attributes.
   * <pre>
   * {@code
   * <entity type="com.symphony.integration.trello.checklist" version="1.0">
   *   <attribute name="name" type="org.symphonyoss.string" value="Subtasks for comment viewing" />
   *   <attribute name="id" type="org.symphonyoss.string" value="57d7f618834f6a46c2badc0a" />
   * </entity>
   * }
   * </pre>
   */
  public Entity toEntity() {
    EntityBuilder builder = EntityBuilder.forNestedEntity(INTEGRATION_NAME, CHECKLIST)
        .attribute(NAME, getName())
        .attribute(ID, getId());

    return builder.build();
  }

}
