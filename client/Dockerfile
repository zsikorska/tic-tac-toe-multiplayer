FROM node:alpine

ENV PORT 80

WORKDIR /app

COPY . /app

RUN npm install --production

EXPOSE 80

CMD ["npm", "start"]