package org.maia.mvc.gerenciadorOfertas.services;

import org.maia.mvc.gerenciadorOfertas.domain.SocialMetaTag;
import org.springframework.stereotype.Service;


public interface SocialMetaTagServices {

	SocialMetaTag getSocialMetaTag(String url);
}
