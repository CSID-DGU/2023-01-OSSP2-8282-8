import { Axios } from "./axios";

const getNoteContent = async (contentId, userId, handlePages) => {
	try {
		const res = await Axios.get(`read/note/`, {
			params: {
				userId: userId,
				noteId: contentId,
			},
		});
		handlePages(res.data.data.notePageUrls);
	} catch (e) {
		console.log(e);
	}
};
export default getNoteContent;
