import React from "react";
import styled from "styled-components";
import ImageBlock from "../molecules/ImageBlcok";
import DateBlock from "../molecules/DateBlock";
import NoteButtonBlock from "../molecules/NoteButtonBlock";

const Container =styled.View`
width:100%;
display: flex;
align-Items:center;
flex-direction:row;
margin-bottom:30px;
`

const NoteUpperDetail = ({img, PublicationDate, ModifiedDate, isStored, truepress, falsepress}) => {
    return(
        <Container>
            <ImageBlock img={img}/>
            <DateBlock PublicationDate={PublicationDate} ModifiedDate={ModifiedDate} />
            <NoteButtonBlock isStored={isStored} truepress={truepress} falsepress={falsepress}/>
        </Container>
    )
}

export default NoteUpperDetail;