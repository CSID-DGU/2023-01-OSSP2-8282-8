import axios from "axios";

const baseURL = "http://172.30.1.3:8080";
// http://현재 wifi의 ip 주소:8080

export const Axios = axios.create({
	baseURL: baseURL,
	headers: {
		"Content-Type": " application/json",
	},
});
