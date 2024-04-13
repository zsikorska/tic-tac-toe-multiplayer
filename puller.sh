#!/bin/bash

if [ -d tic-tac-toe-multiplayer ]; then
    cd tic-tac-toe-multiplayer
else
    git clone https://github.com/zsikorska/tic-tac-toe-multiplayer.git
    cd tic-tac-toe-multiplayer
fi

git pull

docker build -t frontend:latest tic-tac-toe-frontend
docker build -t backend:latest tic-tac-toe-backend

docker compose up -d