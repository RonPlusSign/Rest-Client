# REST Client

This Java program makes requests to a RESTful web service.

---

To use the web service on `localhost`, we use a public `docker container`.

To start the `docker container`, just run the following command:

```bash
docker run --rm -p 8080:8080 -d --name resttest ericgoebelbecker/resttutorial
```

When the container is running, a tutorial that explains how the RESTful web service works can be found on `http://localhost:8080/swagger-ui.html`

