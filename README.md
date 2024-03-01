# social-marketing-services

It includes wahts app integration, FB, Insta integrations
#  Contacts Endpoints



| **Endpoint** | **Description** | **Request Method** |
| --- | --- | --- |
| **/social-marketing-communication/whatsapp/v1/contacts** | Creates a new contact. | POST |
| **/social-marketing-communication/whatsapp/v1/contacts/{id}** | Retrieves a contact by its ID. | GET |
| **/social-marketing-communication/whatsapp/v1/contacts** | Retrieves all contacts. | GET |
|  **/social-marketing-communication/whatsapp/v1/contacts/{id}** | Updates an existing contact identified by its ID. | PUT |
|  **/social-marketing-communication/whatsapp/v1/contacts/{id}** | Deletes a contact by its ID. | DELETE |
| **/social-marketing-communication/whatsapp/v1/contacts/{contactId}** | Creates a new message for a specific contact. | POST |
| **/social-marketing-communication/whatsapp/v1/contacts/{contactId}/template** | Sends a template message to a specific contact. | POST |
| **/social-marketing-communication/whatsapp/v1/contacts/{contactId}/messages** | Retrieves all messages for a specific contact. | GET |
| **/social-marketing-communication/whatsapp/v1/contacts/customers** | Creates contacts with pagination support. | GET |






# Facebook Custom Template  Endpoints 
| **Endpoint** | **Description** | **Request Method** |
| --- | --- | --- |
| **/social-marketing-communication/whatsapp/v1/custom-templates** | Retrieves all custom templates. | GET |
| **/social-marketing-communication/whatsapp/v1/custom-templates/{id}** | Retrieves a custom template by its ID. | GET |
| **/social-marketing-communication/whatsapp/v1/custom-templates** | Creates a new custom template. | POST |
| **/social-marketing-communication/whatsapp/v1/custom-templates/{id}** | Updates an existing custom template identified by its ID. | PUT |
| **/social-marketing-communication/whatsapp/v1/custom-templates/{id}** | Deletes a custom template by its ID. | DELETE |
| **/social-marketing-communication/whatsapp/v1/custom-templates/fb** | Retrieves templates with specified fields and a limit. | GET |
| **/social-marketing-communication/whatsapp/v1/custom-templates** | Deletes a template by name and HSM ID. | DELETE |


## **Facebook Upload Controller API**

| **Endpoint** | **Method** | **Description** |
| --- | --- | --- |
| **/social-marketing-communication/whatsapp/v1/uploads** | POST | Uploads a file to Facebook |
| **/social-marketing-communication/whatsapp/v1/uploads** | GET | Retrieves all uploaded files |

### **Upload File**

Uploads a file to Facebook.

**Endpoint**: **/social-marketing-communication/whatsapp/v1/uploads**

**Method**: POST

**Request Parameters**:

| **Parameter** | **Type** | **Required** | **Description** |
| --- | --- | --- | --- |
| **file** | File | Yes | The file to be uploaded. |

**Response**:

*   Success: Returns the Facebook file handle as a string.
*   Error: Returns an HTTP 500 Internal Server Error with a failure message.

### **Get All Upload Files**

Retrieves all uploaded files from Facebook.

**Endpoint**: **/social-marketing-communication/whatsapp/v1/uploads**

**Method**: GET

**Response**:

*   Success: Returns a list of uploaded files in JSON format.
*   Error: Returns an HTTP 500 Internal Server Error with a failure message.

## **Facebook Messages Controller API**


| **Endpoint** | **Method** | **Description** |
| --- | --- | --- |
| **/messages/{id}** | GET | Retrieves a message by its ID. |
| **/messages** | GET | Retrieves all messages. |
| **/messages/chats** | GET | Retrieves grouped messages by recipient. Scheduled every hour. |

## **Facebook webhooks Controller API**

| **Endpoint** | **Method** | **Description** |
| --- | --- | --- |
| **/webhook** | POST | Handles the WhatsApp webhook message and creates a new contact. |
| **/webhook** | GET | Verifies the webhook subscription by responding with a challenge. |