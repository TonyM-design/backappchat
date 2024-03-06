/* 
package com.tp.chat.service.jsonDeserializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tp.chat.entity.User;
import com.tp.chat.service.UserService;

@Component
public class UserSetDeserializer extends JsonDeserializer<Set<User>> {
    @Autowired
    private UserService userService;

    @Override
    public Set<User> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        ArrayNode arrayNode = mapper.readTree(jsonParser);
        Set<User> users = new HashSet<>();

        for (JsonNode jsonNode : arrayNode) {
            int userId = jsonNode.get("id").asInt();
            Optional<User> user = userService.getUserById(userId);
            user.ifPresent(users::add);
        }

        return users;
    }
}
*/
