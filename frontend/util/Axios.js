import axios from "axios";

const baseURL = "https://localhost:8080/";

export const Axios = axios.create({
	baseURL: baseURL,
	headers: {
		"Content-Type": " application/json",
	},
});
