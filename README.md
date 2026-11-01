# Network Service Request and Fault Management System

**Author:** Paul Joseph Luka Abdallah 27207

## Run the project

1. Open a terminal in the project directory.
2. Install the project dependencies:

	 ```bash
	 npm install
	 ```

3. Configure any required environment variables or services as described in the project configuration.
4. Check `package.json` for the available start script. Common options are:

	 ```bash
	 npm run dev
	 ```

	 or:

	 ```bash
	 npm start
	 ```

5. Use the local URL and port shown in the terminal.

## Test the API

With the application running, send requests using a client such as Postman or `curl`. Substitute an actual route from the API implementation for `<api-route>`.

GET example:

```bash
curl -i -X GET "http://localhost:3000/<api-route>" \
	-H "Accept: application/json"
```

POST example (replace the route and JSON fields with those required by the endpoint):

```bash
curl -i -X POST "http://localhost:3000/<api-route>" \
	-H "Content-Type: application/json" \
	-d '{"key":"value"}'
```

Check the HTTP status and response body. Refer to the project routes and `package.json` for the exact endpoint paths, port, and scripts; these depend on the application configuration.
