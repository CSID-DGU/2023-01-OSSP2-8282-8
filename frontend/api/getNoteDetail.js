import { Axios } from "./axios";

export default getNoteDetail = async (id, userId, handleNoteDetail) => {
	try {
		const res = await Axios.get(`/book/detail`, {
			params: {
				userId: userId,
				bookId: id,
			},
		});
		const data = res.data.data;
		handleNoteDetail(data);
	} catch (e) {
		console.log(e);
	}
};
