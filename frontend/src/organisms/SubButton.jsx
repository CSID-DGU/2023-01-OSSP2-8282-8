import styled from "styled-components";

const Container = styled.TouchableOpacity`
	width: 650px;
	height: 54px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #1d1d1f;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 11px;
`;

const ButtonTypo = styled.Text`
	font-weight: 600;
	font-size: 20px;
	color: #fff;
`;

const SubButton = ({ typo, onPress }) => {
	return (
		<Container onPress={onPress}>
			<ButtonTypo>{typo}</ButtonTypo>
		</Container>
	);
};

export default SubButton;