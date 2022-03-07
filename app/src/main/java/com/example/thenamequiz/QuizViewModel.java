package com.example.thenamequiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Random;

public class QuizViewModel extends ViewModel {

    public void init()
    {
        if (m_quiz_state == null)
            m_quiz_state = new MutableLiveData<>();

        m_quiz_state.postValue(App.database().quiz.get());
    }

    public void did_click(int clicked_index)
    {
        QuizState current_state = m_quiz_state.getValue();
        if (current_state.correct_index == clicked_index) {
            current_state.current_score++;
            if (current_state.current_score > current_state.high_score)
                current_state.high_score = current_state.current_score;
        }
        else {
            current_state.current_score = 0;
        }

        ArrayList<Card> cards = App.database().cards();

        Random r = new Random();

        int one = r.nextInt(cards.size());
        int two = 0;
        while((two = r.nextInt(cards.size())) == one)
            continue;

        int three = 0;
        while((three = r.nextInt(cards.size())) == one || two == three)
            continue;

        current_state.id_one = cards.get(one).id;
        current_state.id_two = cards.get(two).id;
        current_state.id_three = cards.get(three).id;

        int index = r.nextInt(3);

        current_state.correct_index = index;

        App.database().quiz.update(current_state);

        m_quiz_state.postValue(current_state);

    }

    public MutableLiveData<QuizState> get_quiz_state() {
        return m_quiz_state;
    }

    private MutableLiveData<QuizState> m_quiz_state;

}
