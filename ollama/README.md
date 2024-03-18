# Spring AI - Ollama

## Steps to follow
1. Run Ollama locally using Docker.

`docker run -d -v <dir-for-ollama-models>/ollama:/root/.ollama -p 114324:11434 --name ollama ollama/ollama`

`docker exec -it ollama ollama run llama2`

2. Clone this repo and go to ollama directory.
3. Run this spring-boot app. (Make sure you have JDK21.)

`mvn spring-boot:run`

4. Test using curl.

`curl "localhost:8080/chat?prompt=why%20is%20the%20sky%20blue?"`

`curl "localhost:8080/embedding?data=hello%20world"`
