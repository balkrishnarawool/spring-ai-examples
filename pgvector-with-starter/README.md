# Spring AI - PGVector SpringBoot Starter

## Steps to follow
1. Run Ollama locally using Docker. (We need it for creating embeddings.)

`docker run -d -v <dir-for-ollama-models>/ollama:/root/.ollama -p 114324:11434 --name ollama ollama/ollama`

`docker exec -it ollama ollama run llama2`

2. Clone this repo and go to pgvector-with-starter directory.
3. Run pgvector with Docker using docker compose.
   
From pgvector-with-starter directory, run

`docker compose up -d`

This creates 2 containers: one for postgres (with pgvector extension) and another for pgadmin.
To use pgadmin go to `http://localhost:5050`
Use user `pgadmin4@pgadmin.org` and password `admin`
Then go to Server-group in Object Explorer. (OR create a new server group if there is none.)
Then `Register` a new `Server` with these details:
- Name: Local
- Hostname: IP Address of the container (You can get ip-address from Docker desktop. Go to the container and then to details and on the tab Inspect with Raw JSON. Or you can perhaps run some
- 0command .)
- Port: 5432
- Maintenance database: postgres
- Username: postgres
- Password: postgres

Then navigate to Local -> Databases -> vector_store -> Schemas -> public -> Tables -> vectore_store

4. From pgvector-with-starter directory, run this spring-boot app. (Make sure you have JDK21.)

`mvn spring-boot:run`

When you start the app, it adds some data to the vector_store table. So if you are reusing the container, make sure to first wipe the data out with `TRUNCATE TABLE public.vector_store;`. Otherwise, it will add the same data once again.

5. Test using curl.

`curl "localhost:8000/search?query=Square"`

`curl "localhost:8000/search?query=what%20is%20square?"`

`curl "localhost:8000/search?query=multiply"`

`curl "localhost:8000/search?query=toss%20a%20coin"`

`curl "localhost:8000/search?query=a%20random%20number"`
