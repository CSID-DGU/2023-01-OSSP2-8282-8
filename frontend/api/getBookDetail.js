import { Axios } from "./axios";

export default getBookDetail = async (id, userId, handleBookDetail) => {
	try {
		const res = await Axios.get(`/book/detail`, {
			params: {
				userId: userId,
				bookId: id,
			},
		});
		const data = res.data.data;
		handleBookDetail({ ...data, modifiedDate: data.author });
	} catch (e) {
		console.log(e);
	}
};
