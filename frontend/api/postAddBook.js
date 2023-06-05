import { Axios } from "./axios";

export default postAddBook = async (id, userId, handleAddBook) => {
	try {
		const res = await Axios.post("/book/add", {
			userId: userId,
			bookId: id,
		});
		handleAddBook();
	} catch (e) {
		console.log(e);
	}
};
