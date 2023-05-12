import styled from "styled-components";

const Container=styled.View`
width:40%;
display:flex;
flex-direction:row;
align-items:flex-end;
justify-content:center;
`
const ButtonContainer1 = styled.TouchableOpacity`
	width: 40%;
	height: 40px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #56AAF6;
	border-radius: 11px;
    margin-right:20px;
`;

const ButtonContainer2 = styled.TouchableOpacity`
	width: 35%;
	height: 40px;
	display: flex;
	justify-content: center;
	align-items: center;
	background: #000000;
	border-radius: 11px;
`;

const ButtonTypo = styled.Text`
	font-weight: 600;
	font-size: 15px;
	color: #fff;
`;

const CommunityButton1 = ({ typo, onPress }) => {
	return (
		<ButtonContainer1 onPress={onPress}>
			<ButtonTypo>{typo}</ButtonTypo>
		</ButtonContainer1>
	);
};
const CommunityButton2 = ({ typo, onPress }) => {
	return (
		<ButtonContainer2 onPress={onPress}>
			<ButtonTypo>{typo}</ButtonTypo>
		</ButtonContainer2>
	);
};

const NoteHandleButton = ({onPress1,onPress2,isSaled}) => {
    return(
        <Container>
            {isSaled ? (<CommunityButton1 typo="등록된 필기"/>)
            :(<CommunityButton1 typo="필기판매등록" onPress={onPress1}/>)}
            <CommunityButton2 typo="삭제"onPress={onPress2}/>
        </Container>
    )
}

export default NoteHandleButton;