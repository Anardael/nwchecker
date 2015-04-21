package com.nwchecker.server.dao;

import com.nwchecker.server.model.Language;
import java.util.List;

public interface LanguageDAO {
    public void createLanguage(Language language);
    public Language readLanguageById(int id);
    public void updateLanguage(Language language);
    public void deleteLanguageById(int id);
    public int getLanguageIdByTag(String tag);
    public List<Language> getAllLanguages();
}
