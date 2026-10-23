# API Endpoints

## POST - Create a Short URL

`/api/v1/urls`

Creates a new shortened URL.

## GET - Retrieve Short URLs

`/api/v1/urls`  
Retrieves all shortened URLs.

`/api/v1/urls/{code}`  
Retrieves information about a shortened URL by its unique code.

## GET - Redirect

`/{code}`  
Redirects to the original URL using HTTP `302 Found`.

`/health`  
Checks the health status of the service.

## DELETE - Delete Short URLs

`/api/v1/urls/{code}`  
Deletes a shortened URL by its unique code.

`/api/v1/urls`  
Deletes all shortened URLs.