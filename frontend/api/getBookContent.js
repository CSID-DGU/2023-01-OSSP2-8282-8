import { Axios } from "./axios";

export default getBookContent = async (handlePages) => {
	try {
		const res = await Axios.get(`read/book/1`);
		handlePages(res.data.data.pages);
	} catch (e) {
		console.log(e);
	}
};
