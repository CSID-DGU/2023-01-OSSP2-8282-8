import { Axios } from "./axios";

const postSaveNote = async (noteSaveDTO) => {
	try {
		const res = await Axios.post(`/note/save`, noteSaveDTO);
		if (res.data.apiStatus.errorCode == "N200") return true;
		return false;
	} catch (e) {
		console.log(e);
	}
};

export default postSaveNote;
