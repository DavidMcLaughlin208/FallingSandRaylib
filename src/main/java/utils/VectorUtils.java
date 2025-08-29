package utils;

import static com.raylib.Raylib.Vector2;
import static com.raylib.Raylib.Vector3;
import static com.raylib.Raylib.Vector2Length;
import static com.raylib.Raylib.Vector3Length;

public class VectorUtils {

    // Normalizes the provided Vector2 object in place.
    public static void normalize(Vector2 v) {
        float length = Vector2Length(v);
        if (length > 0) {
            v.x(v.x() / length);
            v.y(v.y() / length);
        }
    }

    // Returns a new normalized Vector2, leaving the original unchanged.
    public static Vector2 getNormalized(Vector2 v) {
        float length = Vector2Length(v);
        if (length > 0) {
            float newX = v.x() / length;
            float newY = v.y() / length;
            return new Vector2().x(newX).y(newY);
        }
        return new Vector2().x(0).y(0); // Return a zero vector if magnitude is 0
    }

    public static void normalize(Vector3 v) {
        float length = Vector3Length(v);
        if (length > 0) {
            v.x(v.x() / length);
            v.y(v.y() / length);
        }
    }

    // Returns a new normalized Vector2, leaving the original unchanged.
    public static Vector3 getNormalized(Vector3 v) {
        float length = Vector3Length(v);
        if (length > 0) {
            float newX = v.x() / length;
            float newY = v.y() / length;
            float newZ = v.z() / length;
            return new Vector3().x(newX).y(newY).z(newZ);
        }
        return new Vector3().x(0).y(0).z(0); // Return a zero vector if magnitude is 0
    }

    public static Vector3 copy(Vector3 v) {
        return new Vector3().x(v.x()).y(v.y()).z(v.z());
    }

    public static Vector3 add(Vector3 a, Vector3 b) {
        return new Vector3().x(a.x() + b.x()).y(a.y() + b.y()).z(a.z() + b.z());
    }

    public static Vector3 subtract(Vector3 a, Vector3 b) {
        return new Vector3().x(a.x() - b.x()).y(a.y() - b.y()).z(a.z() - b.z());
    }
}
