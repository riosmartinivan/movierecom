#!/bin/sh

if [ -f /run/secrets/tmdb_api_key ]; then
    export TMDB_API_KEY=$(cat /run/secrets/tmdb_api_key)
fi
if [ -f /run/secrets/netflix_api_key ]; then
    export NETFLIX_API_KEY=$(cat /run/secrets/netflix_api_key)
fi
if [ -f /run/secrets/imdb_api_key ]; then
    export IMDB_API_KEY=$(cat /run/secrets/imdb_api_key)
fi

echo "Starting app"
    
java -jar /app/movierecom.jar --com.example.movierecom.key.tmdbAPIKey="${TMDB_API_KEY}" \
  --com.example.movierecom.key.netflixAPIKey="${NETFLIX_API_KEY}" --com.example.movierecom.key.imdbAPIKey="${IMDB_API_KEY}"
