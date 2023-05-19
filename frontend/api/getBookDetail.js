import { Axios } from "./axios";

export default getBookDetail = async (id) => {
	try {
		const res = await Axios.get(`/book/detail/${id}`);
		console.log("res:", res);
	} catch (e) {
		console.log(e);
	}
};
