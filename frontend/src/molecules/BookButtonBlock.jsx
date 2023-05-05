import styled from "styled-components";
import { TouchableOpacity } from "react-native";

const Container =styled.View`
width:40%
height:200
display:flex;
justify-content:flex-end;
align-items:flex-end;
`
const ButtonWrapper=styled.View`

height:45px;
border-radius:15px;
background: #56AAF6;
align-items: center;
`
const ButtonTypo = styled.Text`
    font-size:20px;
    color: white;
    margin-top:8px;
    margin-bottom:8px;
    margin-left:10px;
    margin-right:10px;
`
const StoreLibraryButton = ({press}) => {
    return(
        <TouchableOpacity onPress={press}>
                <ButtonWrapper>
                    <ButtonTypo>나의 서재에 담기</ButtonTypo>
                </ButtonWrapper>
            </TouchableOpacity>
    )
}

const GotoLibraryButton = ({press}) => {
    return(
        <TouchableOpacity onPress={press}>
                <ButtonWrapper>
                    <ButtonTypo>나의 서재로 가기</ButtonTypo>
                </ButtonWrapper>
            </TouchableOpacity>
    )
}

const BookButtonBlock = ({isStored,truepress,falsepress}) => {
    return(
        <Container>
            {isStored ? <GotoLibraryButton press={truepress}/> : <StoreLibraryButton press={falsepress}/>}
        </Container>
    )
}

export default BookButtonBlock;