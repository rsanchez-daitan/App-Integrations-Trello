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

import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.ACTION;
import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.CARD_TYPE;
import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.CHECKLIST;
import static org.symphonyoss.integration.webhook.trello.TrelloEntityConstants.DATA;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.symphonyoss.integration.entity.EntityBuilder;
import org.symphonyoss.integration.webhook.trello.model.TrelloCard;
import org.symphonyoss.integration.webhook.trello.model.TrelloChecklist;

/**
 * Based parser for board events from Trello.
 * Created by ecarrenho on 08/09/16.
 */
@Component
public abstract class ChecklistTrelloParser extends BaseTrelloParser {

  /**
   * Includes card and checklist as nested entities of data entity.
   */
  @Override
  protected void augmentEventAndDataWithEventEntities(EntityBuilder eventBuilder, EntityBuilder dataBuilder,
      JsonNode payload, JsonNode data) {
    final TrelloCard card = new TrelloCard(payload.path(ACTION).path(DATA).path(CARD_TYPE));
    dataBuilder.nestedEntity(card.toEntity());

    final TrelloChecklist checklist = new TrelloChecklist(getSubjectNode(payload));
    dataBuilder.nestedEntity(checklist.toEntity());
  }

  /**
   * Returns the subject for checklist events parsers.
   * @return Checklist node from Trello payload.
   */
  @Override
  protected JsonNode getSubjectNode(JsonNode payload) {
    return payload.path(ACTION).path(DATA).path(CHECKLIST);
  }

}
