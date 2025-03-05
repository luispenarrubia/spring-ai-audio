package guru.springframework.springaiaudio.services;

import guru.springframework.springaiaudio.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final OpenAiAudioSpeechModel speechModel;

    @Override
    public byte[] getSpeech(Question question) {
        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ONYX)
                .speed(1.0f)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .model(OpenAiAudioApi.TtsModel.TTS_1_HD.value)
                .build();

        SpeechPrompt prompt = new SpeechPrompt(question.question(), options);

        SpeechResponse response = speechModel.call(prompt);

        return response.getResult().getOutput();
    }
}
