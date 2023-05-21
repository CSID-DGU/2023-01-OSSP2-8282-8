import { Axios } from "./axios";

export default getMyNotes = async (userId, handleNotes) => {
	try {
		const res = await Axios.get(`/mynote/${userId}`);
		handleNotes(res.data.data.noteList);
	} catch (e) {
		console.log(e);
	}
};
