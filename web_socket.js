const WebSocket = require('ws');
const kafka = require('kafka-node');

const server = new WebSocket.Server({ port: 8081 });
const client = new kafka.KafkaClient({ kafkaHost: 'localhost:9092' });
const producer = new kafka.Producer(client);

producer.on('ready', () => {
  console.log('Kafka producer is ready.');
});

producer.on('error', (error) => {
  console.error('Kafka producer error:', error);
});

server.on('connection', (socket) => {
  socket.on('message', (data) => {
    // Send data to Kafka when a message is received
    producer.send([{ topic: 'topic-1', messages: data }], (err, data) => {
      if (err) {
        console.error('Error sending data to Kafka:', err);
      }
    });
  });
});
