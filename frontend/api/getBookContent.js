import { Axios } from "./axios";

export default getBookContent = async (contentId, handlePages) => {
	try {
		const res = await Axios.get(`read/book/${contentId}`);
		handlePages(res.data.data.pages);
	} catch (e) {
		console.log(e);
	}
};
