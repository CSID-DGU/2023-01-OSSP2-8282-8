import styled from "styled-components";

const Input = styled.TextInput`
	width: 100%;
	height: 48px;
	background: #fff;
	border-radius: 10px;
	margin: 5.5px 0;
	padding: 6px;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	font-weight: 400;
	font-size: 12px;
	color: #cdcdcd;
`;

const CommunityInput = ({ typo, changeHandler, textInputRef }) => {
	return (
		<Input placeholder={typo} onChangeText={changeHandler} ref={textInputRef} />
	);
};

export default CommunityInput;
