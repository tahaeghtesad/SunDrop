****Authorization****
                                                           _____________________
                                                           |                   |
                                                           |                   |
grant_type: client_credentials                             |                   |
---------------------------------------------------------> |                   |  ~~~~~~~> used for registration
Header ~> Authorization: Basic <base64: clientDetails>     |   /oauth/token    |
                                                           |       {POST}      |
                                                           |                   |--------------------------> TOKEN
grant_type: password                                       |                   |
username:<username>                                        |                   |
password:<password>                                        |                   |
---------------------------------------------------------> |                   |  ~~~~~~~> used for anything else
Header ~> Authorization: Basic <base64: clientDetails>     |                   |
                                                           _____________________

****ENDPOINTS****
(use with
    **Authorization: Bearer <token>**
         in request header)

_________________________________________________________________________________________________________________________
| method |          url                |        body parameters           | description
|________|_____________________________|__________________________________|______________________________________________
| GET    | /api/me                     |                                  | user details of requesting user
| POST   | /api/user/                  | password:String                  | change password
| PUT    | /api/user/                  | username:String, password:String | new user
| PUT    | /api/directory              | name:String, parentId: Long      | create directory with name {name} in directory with parentId {parentId}
| DELETE | /api/directory/{id}         |                                  | delete directory with id {id}
| GET    | /api/directory/{id}         |                                  | get details of directory with id {id}
| GET    | /api/directory/listdir/{id} |                                  | list subdirectories of directory with id {id}
| GET    | /api/directory/listfile/{id}|                                  | list subfiles of directory with id {id} 
| GET    | /api/directory/parent/{id}  |                                  | get parent directory of directory with id {id}
| POST   | /api/file/                  |name:S, parentDir:L, file:File    | upload a file to directory with id {parentDir} and name it {name} 
| GET    | /api/file/{id}              |                                  | download file with id {id}
| GET    | /api/file/info/{id}         |                                  | get description of a file with id {id}
| DELETE | /api/file/{id}              |                                  | delete file with id {id}
| GET    | /api/file/parent/{id}       |                                  | get parent directory of file with id {id}
|________|_____________________________|__________________________________|______________________________________________
