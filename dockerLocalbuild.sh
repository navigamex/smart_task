#!/bin/bash

docker build -t smart-task:latest .

# 查看构建的 Docker 镜像列表
echo "Docker image list:"
docker images

# 运行 Docker 容器
echo "Running the Docker container..."
docker stop smart-task;
docker rm smart-task;
docker run -d   --network docker_network --ip 172.18.0.88 --name smart-task smart-task:latest

# 查看容器日志 (可以帮助调试)
echo "Viewing the logs of the running container..."
docker logs -f smart-task

# 结束时显示所有容器
echo "Showing all running containers:"
docker ps


