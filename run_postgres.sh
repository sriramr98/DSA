docker run -itd -e POSTGRES_USER=dsatest -e POSTGRES_PASSWORD=dsatest -p 5432:5432 -v $PWD/data:/var/lib/postgresql/data --name postgresql postgres
