package com.pdfcampus.pdfcampus.service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;

public class GetPDFDimensions {

    public static float[] getPageDimensions(PDDocument document, int pageNumber) {
        PDPage page = document.getPage(pageNumber);
        float width = page.getMediaBox().getWidth();
        float height = page.getMediaBox().getHeight();
        return new float[]{width, height};
    }
}

