Swagger UI for Sling/AEM (Open API Initiative)
====

This project takes the distribution folder from the swagger-ui project at
(https://github.com/swagger-api/swagger-ui) and repackages it so it can be used
within Apache Sling and Adobe Experience Manager

current swagger-ui version: `3.0.5`

# motivation

The swagger-ui is an api documentation tool that consumes OpenAPI definition files
and creates an interactive documentation from it. 

[OpenAPI Spec](https://github.com/OAI/OpenAPI-Specification)


A simple Echo Example (yaml, json is also suppored)
```
swagger: 2
info:
  title: Echo API
  description: Simple Rest Echo
  version: "1.0.0"
host: "localhost:8080"
schemes:
  - http
basePath: /v1
produces:
  - application/json
paths:
  /echo:
    get:
      description: "Returns the 'message' to the caller"
      operationId: "echo"
      parameters:
        #- name: X-header-param
        - name: headerParam
          in: header
          type: string
          required: false
        - name: message
          in: query
          type: string
          required: true
      responses:
        200:
          description: "Success"
          schema:
            $ref: EchoResponse
        default:
          description: "Error"
          schema:
            $ref: Error
definitions:
  EchoResponse:
    required:
      - message
    properties:
      message:
        type: string
  Error:
    properties:
      code:
        type: integer
        format: int32
      message:
        type: string
      fields:
        type: string

```

# to install into a local instance

## for sling
```mvn clean install -PautoInstallPackage```

## for aem
```mvn clean install -PautoInstallPackage,aem```

# to use

A list of all open api files in an instrance can be found at
`/content/swagger/apis.html`[sling](http://localhost:8080/etc/openapi.html)
[AEM](http://localhost:4502/etc/openapi.html)

# add your own openapi definition files

To add your own OpenAPI definition files create a `sling:Folder` node in your
repository and add a property `openapi={Boolean}true` to the node. All immediate
children of `nt:file` will be listed in the `/etc/openapi.html` list. 

# notes

This project is only supported on sling instances with composum installed as it
uses regular content packages for its installation (starting with sling-9 composum
is included by default). 

