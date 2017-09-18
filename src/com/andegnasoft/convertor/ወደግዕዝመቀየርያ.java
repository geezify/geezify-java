/*
 * The MIT License
 *
 * Copyright 2016 SamAsEnd.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.andegnasoft.convertor;

/**
 *
 * @author SamAsEnd <4sam21{at}gmail.com>
 */
public class ወደግዕዝመቀየርያ {

    protected static final char ግዕዝ_አንድ = '፩';
    protected static final char ግዕዝ_አስር = '፲';
    protected static final char ግዕዝ_መቶ = '፻';
    protected static final char ግዕዝ_አስር_ሺ = '፼';

    protected static final char ባዶ_ፊደል = ' ';
    protected static final String ባዶ_ቃል = "";

    protected static final int ዜሮ = 0;
    protected static final int አንድ = 1;
    protected static final int ሁለት = 2;
    protected static final int አስር = 10;
    protected static final int መቶ = 100;

    public String ቀይር(long ቁጥር) {
        String የመጨረሻ_ውጤት = ባዶ_ቃል;
        int ቤት = ዜሮ;

        while (ቁጥር > ዜሮ) {
            long ሁለቲ_ቁጥሮች = ቁጥር % መቶ;

            String የግዕዙ_ቁጥሩ;
            char አካፋይ;

            ቁጥር /= መቶ;

            የግዕዙ_ቁጥሩ = ሁለቱን_ቁጥሮች_ገምት(ሁለቲ_ቁጥሮች);
            አካፋይ = አካፋዩን_ገምት(ቤት);

            የመጨረሻ_ውጤት = የግዕዙን_ቁጥር_ከአካፋዩ_ደባልቅ(ሁለቲ_ቁጥሮች, አካፋይ, ቁጥር, የግዕዙ_ቁጥሩ) + የመጨረሻ_ውጤት;

            ቤት++;
        }

        return የመጨረሻ_ውጤት.replace(" ", "");
    }

    protected String የግዕዙን_ቁጥር_ከአካፋዩ_ደባልቅ(long ሁለቲ_ቁጥሮች, char አካፋይ, long ቁጥር, String የግዕዙ_ቁጥሩ) {
        if (አካፋዩ_መጥፋት_አለበት(ሁለቲ_ቁጥሮች, አካፋይ)) {
            አካፋይ = ባዶ_ፊደል;
        }

        if (የግዕዝ_ቁጥሩ_መጥፋት_አለበት(ሁለቲ_ቁጥሮች, አካፋይ, ቁጥር)) {
            የግዕዙ_ቁጥሩ = ባዶ_ቃል;
        }

        return ባዶ_ቃል + የግዕዙ_ቁጥሩ + አካፋይ;
    }

    protected char አካፋዩን_ገምት(int ቤት) {
        char አካፋይ;
        if (ቤት == ዜሮ) {
            አካፋይ = ' ';
        } else if (ጎዶሎ_ቁጥር_ከሆነ(ቤት)) {
            አካፋይ = ግዕዝ_መቶ;
        } else {
            አካፋይ = ግዕዝ_አስር_ሺ;
        }
        return አካፋይ;
    }

    protected static boolean የግዕዝ_ቁጥሩ_መጥፋት_አለበት(long ሁለቲ_ቁጥሮች, int አካፋይ, long ቁጥር) {
        return (ሁለቲ_ቁጥሮች == 1 && አካፋይ == ግዕዝ_መቶ)
                || (ቁጥር == ዜሮ && ሁለቲ_ቁጥሮች == 1 && አካፋይ == ግዕዝ_አስር_ሺ);
    }

    protected static boolean አካፋዩ_መጥፋት_አለበት(long ሁለቲ_ቁጥሮች, int አካፋይ) {
        return ሁለቲ_ቁጥሮች == ዜሮ && አካፋይ == ግዕዝ_መቶ;
    }

    protected static boolean ጎዶሎ_ቁጥር_ከሆነ(int ቤት) {
        return ቤት % ሁለት == አንድ;
    }

    protected String ሁለቱን_ቁጥሮች_ገምት(long ሁለቱ_ቁጥሮች) {
        byte አስር_ቤት = (byte) (ሁለቱ_ቁጥሮች / አስር);
        byte አንድ_ቤት = (byte) (ሁለቱ_ቁጥሮች % አስር);

        String በግዕዝ = "";

        በግዕዝ += (አስር_ቤት > ዜሮ ? (char) (አስር_ቤት + ግዕዝ_አስር - አንድ) : "");
        በግዕዝ += (አንድ_ቤት > ዜሮ ? (char) (አንድ_ቤት + ግዕዝ_አንድ - አንድ) : "");

        return በግዕዝ;
    }

}
