import { Axios } from "./axios";

export default postDeleteNote = async (postDeleteNoteDTO) => {
	try {
		const res = await Axios.post(`/mynote/delete`, postDeleteNoteDTO);
		console.log("res:", res);
	} catch (e) {
		console.log(e);
	}
};
