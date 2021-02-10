#version 330

in vec2 textureCoords;
out vec4 out_Color;

uniform vec4 hoveredColor = vec4(0.2, 0.2, 0.4, 0.0);
uniform vec4 trans = vec4(1.0, 1.0, 1.0, 0.8);

uniform vec3 color;
uniform sampler2D guiTexture;
uniform bool isPictureMode;
uniform bool isHovered;
void main(void){
  vec4 c;
  if(isPictureMode){
    c = texture(guiTexture, textureCoords);
  } else {
      c = vec4(color, 1.0);
  }

  out_Color = isHovered ? c + hoveredColor : c;
  out_Color = out_Color * trans;
}


