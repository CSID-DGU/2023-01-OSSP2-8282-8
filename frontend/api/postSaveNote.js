import { Axios } from "./axios";

const postSaveNote = async (noteSaveDTO) => {
	try {
		const res = await Axios.post(`/note/save`, noteSaveDTO);
		console.log("res:", res);
	} catch (e) {
		console.log(e);
	}
};

export default postSaveNote;
