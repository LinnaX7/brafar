import axios from 'axios'
const BaseURL = 'http://localhost:8080/'

export const request = axios.create({
  timeout: 10000, // 请求超时时间
  baseURL: BaseURL,
})
