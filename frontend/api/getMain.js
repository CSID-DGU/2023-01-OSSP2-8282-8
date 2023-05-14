import { Axios } from "./axios";

export default getMain = async (handleContents) => {
	try {
		const res = await Axios.get(`/main`);
		const data = res.data.data;
		const { bookList, noteList } = data;

		handleContents(bookList, noteList);
	} catch (e) {
		console.log(e);
	}
};