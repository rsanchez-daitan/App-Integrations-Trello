_Note that this project depends on internal Symphony infrastructure (repository.symphony.com), and therefore it can only be built by Symphony LLC employees/partners._

# Trello WebHook Integration
With the Trello WebHook Integration you can configure which Trello boards you want to receive notifications from, and what sort of activity you want to be notified about.

## How it works
As a Symphony user you can configure a WebHook with Trello directly from the Trello WebHook Application available on Symphony Market. There you can choose which events you want to be notified from Trello, no further setup required.

## What formats and events it support and what it produces
Every integration will get a message sent in a specific format (depending on what system is it dealing with) and it will usually convert it into an "entity" before it reaches the Symphony platform.
It will also, usually, identify the kind of message it will deal with based on an "event" identifier, that varies based on which system is it integrating with.

Currently we support up to eighteen events from Trello grouped in four main categories, which we'll go into details bellow:

### Boards
###### "updateBoard"
* Event maps to "boardRenamed"

[JSON payload](src/test/resources/payload_trello_board_updated.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.updateBoard" version="1.0">
      <presentationML>
        Evandro Carrenho @symphony renamed Board New Name (from Board renamed) (<a href="https://trello.com/b/t0hu7Ffx" />)
      </presentationML>
      <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-09-12T15:58:42.540Z" />
      <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
        <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphonytest" />
        <attribute name="emailAddress" type="org.symphonyoss.string" value="ecarrenhosymphonytest@symphony.com" />
        <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho @symphony" />
        <entity type="com.symphony.mention" version="1.0">
          <attribute name="id" type="org.symphony.oss.number.long" value="699722783" />
          <attribute name="name" type="org.symphonyoss.string" value="Evandro Carrenho @symphony" />
        </entity>
      </entity>
      <entity type="com.symphony.integration.trello.data" version="1.0">
        <entity type="com.symphony.integration.trello.board" version="1.0">
          <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx" />
          <attribute name="name" type="org.symphonyoss.string" value="Board New Name" />
          <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca" />
          <entity type="com.symphony.integration.trello.prefs" version="1.0">
            <attribute name="background" type="org.symphonyoss.string" value="blue" />
            <attribute name="backgroundColor" type="org.symphonyoss.string" value="#0079BF" />
          </entity>
        </entity>
        <entity type="com.symphony.integration.trello.changelog" version="1.0">
          <entity type="com.symphony.integration.trello.change" version="1.0">
            <attribute name="fieldName" type="org.symphonyoss.string" value="name" />
            <attribute name="oldValue" type="org.symphonyoss.string" value="Board renamed" />
            <attribute name="newValue" type="org.symphonyoss.string" value="Board New Name" />
          </entity>
        </entity>
      </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "updateBoard" rendered](src/docs/sample/sample_board_updated_rendered.png)

###### "addMemberToBoard"
* Event maps to "memberAddedToBoad"

[JSON payload](src/test/resources/payload_trello_board_member_added.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.addMemberToBoard" version="1.0">
        <presentationML>
            Evandro Carrenho @symphony added Evandro Carrenho to Board renamed (<a href="https://trello.com/b/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-09-12T15:41:52.030Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphonytest"/>
            <attribute name="emailAddress" type="org.symphonyoss.string" value="ecarrenhosymphonytest@symphony.com"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho @symphony"/>
            <entity type="com.symphony.mention" version="1.0">
                <attribute name="id" type="org.symphony.oss.number.long" value="699722784"/>
                <attribute name="name" type="org.symphonyoss.string" value="Evandro Carrenho @symphony"/>
            </entity>
        </entity>
        <entity name="member" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="evandrocarrenho"/>
            <attribute name="emailAddress" type="org.symphonyoss.string" value="evandrocarrenho@symphony.com"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
            <entity type="com.symphony.mention" version="1.0">
                <attribute name="id" type="org.symphony.oss.number.long" value="699722783"/>
                <attribute name="name" type="org.symphonyoss.string" value="Evandro Carrenho"/>
            </entity>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <attribute name="idMemberAdded" type="org.symphonyoss.string" value="57a10c3b4ba8a7f29b3c238a"/>
            <attribute name="memberType" type="org.symphonyoss.string" value="normal"/>
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="Board renamed"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
                <entity type="com.symphony.integration.trello.prefs" version="1.0">
                    <attribute name="background" type="org.symphonyoss.string" value="blue"/>
                    <attribute name="backgroundColor" type="org.symphonyoss.string" value="#0079BF"/>
                </entity>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "addMemberToBoard" rendered](src/docs/sample/sample_board_member_added_rendered.png)

###### "addToOrganizationBoard"
* Event maps to "boardAddedToTeam"

[JSON payload](src/test/resources/payload_trello_board_added_team.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.addToOrganizationBoard" version="1.0">
        <presentationML>
            Evandro Carrenho @symphony added Board renamed to My Trello Team (<a href="https://trello.com/b/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-09-12T14:36:16.102Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphonytest"/>
            <attribute name="emailAddress" type="org.symphonyoss.string" value="ecarrenhosymphonytest@symphony.com"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho @symphony"/>
            <entity type="com.symphony.mention" version="1.0">
                <attribute name="id" type="org.symphony.oss.number.long" value="699722783"/>
                <attribute name="name" type="org.symphonyoss.string" value="Evandro Carrenho @symphony"/>
            </entity>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="Board renamed"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
                <entity type="com.symphony.integration.trello.prefs" version="1.0">
                    <attribute name="background" type="org.symphonyoss.string" value="blue"/>
                    <attribute name="backgroundColor" type="org.symphonyoss.string" value="#0079BF"/>
                </entity>
            </entity>
            <entity type="com.symphony.integration.trello.organization" version="1.0">
                <attribute name="id" type="org.symphonyoss.string" value="57a10d35e68764461773a275"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Trello Team"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

* missing sample
![Sample "addToOrganizationBoard" rendered](src/docs/sample/sample_board_added_to_team_rendered.png)

### Cards
###### "createCard"
* Event maps to "cardCreated"

[JSON payload](src/test/resources/payload_trello_card_created.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.createCard" version="1.0">
        <presentationML>
            Joe Smith added My Card to My List (in My &lt;b&gt;Trello&lt;/b&gt;<br/><br/>Board) (<a href="https://trello.com/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-08-04T14:24:09.667Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="jsmith"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Joe Smith"/>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string"
                           value="My &amp;lt;b&amp;gt;Trello&amp;lt;/b&amp;gt;&lt;br/&gt;&lt;br/&gt; Board"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
            </entity>
            <entity type="com.symphony.integration.trello.list" version="1.0">
                <attribute name="name" type="org.symphonyoss.string" value="My List"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34bdaa4050393a6f15a24"/>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="thH8QUqG"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Card"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a4a5aad6759eba0f594ec2"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="1"/>
                <attribute name="desc" type="org.symphonyoss.string" value="My Card Description"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "createCard" rendered](src/docs/sample/)

###### "updateCard"
* Event maps to:
1. "cardArchivedUnarchived" [JSON payload](src/test/resources/payload_trello_card_archived.json)

![sample rendered](src/docs/sample/sample_card_updated_archived_rendered.png)
2. "cardMoved" [JSON payload](src/test/resources/payload_trello_card_moved.json)

![sample rendered](src/docs/sample/sample_card_updated_moved_rendered.png)
3. "cardRenamed" [JSON payload](src/test/resources/payload_trello_card_renamed.json)

![sample rendered](src/docs/sample/sample_card_updated_renamed_rendered.png)
4. "cardDueDateChanged" [JSON payload](src/test/resources/payload_trello_card_due_date_changed.json)

![sample rendered](src/docs/sample/sample_card_updated_due_date_changed_rendered.png)
5. "cardDescriptionChanged" [JSON payload](src/test/resources/payload_trello_card_description_changed.json)

![sample rendered](src/docs/sample/sample_card_updated_desc_changed_rendered.png)

###### "commentCard"
* Event maps to "commentAddedToCard"
[JSON payload](src/test/resources/payload_trello_card_commented.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.commentCard" version="1.0">
        <presentationML>
            User Test on My Card<br/>
            "My comment on this card"
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-08-04T14:28:37.415Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="usertest"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="User Test"/>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <attribute name="comment" type="org.symphonyoss.string" value="My comment on this card"/>
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Trello Board"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
            </entity>
            <entity type="com.symphony.integration.trello.list" version="1.0">
                <attribute name="name" type="org.symphonyoss.string" value="My List 1"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34bdaa4050393a6f15a24"/>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="QazveT2S"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Card"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a35009784b0df0be746cbc"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="1"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "commentCard" rendered](src/docs/sample/sample_card_commented_rendered.png)

###### "addAttachmentToCard"
* Event maps to "attachmentAddedToCard"

[JSON payload](src/test/resources/payload_trello_attachment_dropbox.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.addAttachmentToCard" version="1.0">
        <presentationML>
            Evandro Carrenho add the attachment "How to use the Public folder.rtf" (<a href="https://www.dropbox.com/s/52ftsudpz665u5p/How%20to%20use%20the%20Public%20folder.rtf?dl=0"/>) to Renamed card in Another Trello Board (<a href="https://trello.com/b/N4sE9Ueu"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-09-27T16:13:38.793Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphonytest"/>
            <attribute name="emailAddress" type="org.symphonyoss.string" value="ecarrenhosymphonytest@symphony.com"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
            <entity type="com.symphony.mention" version="1.0">
                <attribute name="id" type="org.symphony.oss.number.long" value="699722783"/>
                <attribute name="name" type="org.symphonyoss.string" value="Evandro Carrenho"/>
            </entity>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/N4sE9Ueu"/>
                <attribute name="name" type="org.symphonyoss.string" value="Another Trello Board"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34f3e36e1e889b0dfca55"/>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="dUz5fzV0"/>
                <attribute name="name" type="org.symphonyoss.string" value="Renamed card"/>
                <attribute name="id" type="org.symphonyoss.string" value="57ea99ab4e60503af5708af4"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="1"/>
            </entity>
            <entity type="com.symphony.integration.trello.attachment" version="1.0">
                <attribute name="url" type="com.symphony.uri" value="https://www.dropbox.com/s/52ftsudpz665u5p/How%20to%20use%20the%20Public%20folder.rtf?dl=0"/>
                <attribute name="name" type="org.symphonyoss.string" value="How to use the Public folder.rtf"/>
                <attribute name="id" type="org.symphonyoss.string" value="57ea9b75d2198ee98e034d18"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "addAttachmentToCard" rendered](src/docs/sample/sample_card_attachment_added_rendered.png)

###### "addLabelToCard"
* Event maps to "cardLabelChanged"

[JSON payload](src/test/resources/payload_trello_label_added.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.addLabelToCard" version="1.0">
        <presentationML>
            Evandro Carrenho added the label "Black Label" to My Card 1 (in My Trello Board) (<a href="https://trello.com/b/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-08-05T16:48:01.304Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphony"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Trello Board"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="thH8QUqG"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Card 1"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a4a5aad6759eba0f594ec2"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="7"/>
            </entity>
            <entity type="com.symphony.integration.trello.label" version="1.0">
                <attribute name="color" type="org.symphonyoss.string" value="black"/>
                <attribute name="name" type="org.symphonyoss.string" value="Black Label"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b3384e677fd36cc13b8"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "addLabelToCard" rendered](src/docs/sample/sample_label_added_rendered.png)

###### "removeLabelFromCard"
* Event maps to "cardLabelChanged"

[JSON payload](src/test/resources/payload_trello_label_removed.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.removeLabelFromCard" version="1.0">
        <presentationML>
            Evandro Carrenho removed the label "Black Label" from My Card 1 (in My Trello Board) (<a href="https://trello.com/b/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-08-05T16:48:01.304Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphony"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Trello Board"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="thH8QUqG"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Card 1"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a4a5aad6759eba0f594ec2"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="7"/>
            </entity>
            <entity type="com.symphony.integration.trello.label" version="1.0">
                <attribute name="color" type="org.symphonyoss.string" value="black"/>
                <attribute name="name" type="org.symphonyoss.string" value="Black Label"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b3384e677fd36cc13b8"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "removeLabelFromCard" rendered](src/docs/sample/sample_label_removed_rendered.png)

###### "addMemberToCard"
* Event maps to "memberAddedToCard"

[JSON payload](src/test/resources/payload_trello_card_member_added.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.addMemberToCard" version="1.0">
        <presentationML>
            Test User added Evandro Carrenho to My Card 1 (in My Trello Board) (<a href="https://trello.com/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-08-05T16:52:10.237Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="testuser"/>
            <attribute name="emailAddress" type="org.symphonyoss.string" value="testuser@symphony.com"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Test User"/>
            <entity type="com.symphony.mention" version="1.0">
                <attribute name="id" type="org.symphony.oss.number.long" value="699722783"/>
                <attribute name="name" type="org.symphonyoss.string" value="Test User"/>
            </entity>
        </entity>
        <entity name="member" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="evandrocarrenho"/>
            <attribute name="emailAddress" type="org.symphonyoss.string" value="evandrocarrenho@symphony.com"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
            <entity type="com.symphony.mention" version="1.0">
                <attribute name="id" type="org.symphony.oss.number.long" value="699722784"/>
                <attribute name="name" type="org.symphonyoss.string" value="Evandro Carrenho"/>
            </entity>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <attribute name="idMember" type="org.symphonyoss.string" value="547da2aa2c96e3655d7c1612"/>
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Trello Board"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="thH8QUqG"/>
                <attribute name="name" type="org.symphonyoss.string" value="My Card 1"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a4a5aad6759eba0f594ec2"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="7"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "addMemberToCard" rendered](src/docs/sample/sample_card_member_added_rendered.png)

###### "convertToCardFromCheckItem"
* Event stays the same

[JSON payload](src/test/resources/payload_trello_card_from_checkitem.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.convertToCardFromCheckItem" version="1.0">
        <presentationML>
            Evandro Carrenho converted Comments entity builder from a checklist item on Need to add the ability to see comments (<a href="https://trello.com/b/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-09-13T16:18:38.654Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphonytest"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="Symphony Innovate"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
                <entity type="com.symphony.integration.trello.prefs" version="1.0">
                    <attribute name="background" type="org.symphonyoss.string" value="blue"/>
                    <attribute name="backgroundColor" type="org.symphonyoss.string" value="#0079BF"/>
                </entity>
            </entity>
            <entity type="com.symphony.integration.trello.list" version="1.0">
                <attribute name="name" type="org.symphonyoss.string" value="Do Trello Parsers"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d6ec9303e4f6b21c1696a9"/>
            </entity>
            <entity name="card" type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="name" type="org.symphonyoss.string" value="Comments entity builder"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d826de3fbb7c89a046dfac"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="13"/>
            </entity>
            <entity name="cardSource" type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="woQM9Qqo"/>
                <attribute name="name" type="org.symphonyoss.string" value="Need to add the ability to see comments"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d6ecdfcdddf493280e0d1b"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="11"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "convertToCardFromCheckItem" rendered](src/docs/sample/sample_card_from_checkitem_rendered.png)


### Checklists
###### "addChecklistToCard"
* Event maps to "checklistCreated"

[JSON payload](src/test/resources/payload_trello_checklist_created.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.addChecklistToCard" version="1.0">
        <presentationML>
            Evandro Carrenho added Subtasks for comments viewing to Need to add the ability to see comments (<a href="https://trello.com/b/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-09-13T12:50:32.129Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphonytest"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="Symphony Innovate"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
                <entity type="com.symphony.integration.trello.prefs" version="1.0">
                    <attribute name="background" type="org.symphonyoss.string" value="blue"/>
                    <attribute name="backgroundColor" type="org.symphonyoss.string" value="#0079BF"/>
                </entity>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="woQM9Qqo"/>
                <attribute name="name" type="org.symphonyoss.string" value="Need to add the ability to see comments"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d6ecdfcdddf493280e0d1b"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="11"/>
            </entity>
            <entity type="com.symphony.integration.trello.checklist" version="1.0">
                <attribute name="name" type="org.symphonyoss.string" value="Subtasks for comments viewing"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d7f618834f6a46c2badc0a"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "addChecklistToCard" rendered](src/docs/sample/sample_checklist_created_rendered.png)

###### "createCheckItem"
* Event maps to "checklistItemCreated"

[JSON payload](src/test/resources/payload_trello_checkitem_create.json)

Converted to Symphony Message ML
```xml
<messageML>
    <entity type="com.symphony.integration.trello.event.createCheckItem" version="1.0">
        <presentationML>
            Evandro Carrenho added Comments renderer to Subtasks for comments viewing in Need to add the ability to see comments (<a href="https://trello.com/b/t0hu7Ffx"/>)
        </presentationML>
        <attribute name="date" type="org.symphonyoss.time.rfc3339" value="2016-09-13T13:34:53.901Z"/>
        <entity name="memberCreator" type="com.symphony.integration.trello.user" version="1.0">
            <attribute name="username" type="org.symphonyoss.string" value="ecarrenhosymphonytest"/>
            <attribute name="displayName" type="org.symphonyoss.string" value="Evandro Carrenho"/>
        </entity>
        <entity type="com.symphony.integration.trello.data" version="1.0">
            <entity type="com.symphony.integration.trello.board" version="1.0">
                <attribute name="shortUrl" type="com.symphony.uri" value="https://trello.com/b/t0hu7Ffx"/>
                <attribute name="name" type="org.symphonyoss.string" value="Symphony Innovate"/>
                <attribute name="id" type="org.symphonyoss.string" value="57a34b33b350eb2bcc1f42ca"/>
                <entity type="com.symphony.integration.trello.prefs" version="1.0">
                    <attribute name="background" type="org.symphonyoss.string" value="blue"/>
                    <attribute name="backgroundColor" type="org.symphonyoss.string" value="#0079BF"/>
                </entity>
            </entity>
            <entity type="com.symphony.integration.trello.card" version="1.0">
                <attribute name="shortLink" type="org.symphonyoss.string" value="woQM9Qqo"/>
                <attribute name="name" type="org.symphonyoss.string" value="Need to add the ability to see comments"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d6ecdfcdddf493280e0d1b"/>
                <attribute name="idShort" type="org.symphony.oss.number.int" value="11"/>
            </entity>
            <entity type="com.symphony.integration.trello.checklist" version="1.0">
                <attribute name="name" type="org.symphonyoss.string" value="Subtasks for comments viewing"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d7f618834f6a46c2badc0a"/>
            </entity>
            <entity type="com.symphony.integration.trello.checkItem" version="1.0">
                <attribute name="state" type="org.symphonyoss.string" value="incomplete"/>
                <attribute name="name" type="org.symphonyoss.string" value="Comments renderer"/>
                <attribute name="id" type="org.symphonyoss.string" value="57d8007dd4b23351441c4cde"/>
            </entity>
        </entity>
    </entity>
</messageML>
```
Message rendered on Symphony

![Sample "createCheckItem" rendered](src/docs/sample/sample_checklist_item_created_rendered.png)

###### "updateCheckItem"
[JSON payload](src/test/resources/)

Converted to Symphony Message ML
```xml
<messageML>
    
</messageML>
```
Message rendered on Symphony

![Sample "updateCheckItem" rendered](src/docs/sample/)

###### "updateCheckItemStateOnCard"
[JSON payload](src/test/resources/)

Converted to Symphony Message ML
```xml
<messageML>
    
</messageML>
```
Message rendered on Symphony

![Sample "updateCheckItemStateOnCard" rendered](src/docs/sample/)


### Lists
###### "createList"
[JSON payload](src/test/resources/)

Converted to Symphony Message ML
```xml
<messageML>
    
</messageML>
```
Message rendered on Symphony

![Sample "createList" rendered](src/docs/sample/)

###### "updateList"
[JSON payload](src/test/resources/)

Converted to Symphony Message ML
```xml
<messageML>
    
</messageML>
```
Message rendered on Symphony

![Sample "updateList" rendered](src/docs/sample/)

###### "moveListFromBoard"
[JSON payload](src/test/resources/)

Converted to Symphony Message ML
```xml
<messageML>
    
</messageML>
```
Message rendered on Symphony

![Sample "moveListFromBoard" rendered](src/docs/sample/)


