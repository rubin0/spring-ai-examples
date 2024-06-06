package dev.arubino.springai;

import java.util.*;

public record Pokemon(int id, String name, String type, String region, List<String> abilities) {
}
