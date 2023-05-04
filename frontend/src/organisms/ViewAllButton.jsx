import styled from "styled-components";

const Container = styled.TouchableOpacity`
	width: 100%;
	height: 41px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #56AAF6;
	box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
	border-radius: 15px;
`;

const ButtonTypo = styled.Text`
	font-weight: 600;
	font-size: 16px;
	color: #fff;
`;

const ViewAllButton = ({ typo, onPress }) => {
	return (
		<Container onPress={onPress}>
			<ButtonTypo>{typo}</ButtonTypo>
		</Container>
	);
};

export default ViewAllButton;
