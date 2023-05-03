import { Axios } from "./Axios";

export const postSignUp = async (signUpData) => {
	const res = await Axios.post("/signUp", signUpData);
	console.log("res: ", res);
};
