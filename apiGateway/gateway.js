const express = require('express');
const { createProxyMiddleware } = require('http-proxy-middleware');
const morgan = require('morgan');
const cors = require('cors');

const app = express();
const PORT = 3000;

app.use(morgan('dev'));
app.use(cors());


// Rota principal (teste)
app.get('/', (req, res) => {
  res.send('Gateway ativo e roteando requisições!');
});

app.listen(PORT, () => {
  console.log(`🚀 API Gateway rodando em http://localhost:${PORT}`);
});

// Rota para o microserviço de autenticação
app.use('/auth', createProxyMiddleware({
  target: 'http://localhost:8081/auth',
  changeOrigin: true,
  logLevel: 'debug',   
}));
