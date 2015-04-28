package com.nwchecker.server.dao;

import com.nwchecker.server.model.Language;
import java.util.List;

public interface LanguageDAO {
    void createLanguage(Language language);
    Language readLanguageById(int id);
    void updateLanguage(Language language);
    void deleteLanguageById(int id);
    int getLanguageIdByTag(String tag);
    List<Language> getAllLanguages();
}
