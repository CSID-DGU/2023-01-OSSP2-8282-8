import { AxiosToken } from "./axios";

export default getMyLib = async (userId, handleContents) => {
	try {
		const res = await AxiosToken.get(`/mylib/${userId}`);
		const data = res.data.data;
		const { bookList, noteList, isNoteMore, isBookMore } = data;

		handleContents(bookList, noteList, isBookMore, isNoteMore);
	} catch (e) {
		console.log(e);
	}
};
